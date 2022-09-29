
import * as fs from "fs";
import * as path from "path";
import { parse } from 'csv-parse';
import { SaveCustomerClient, GetCustomersClient } from './src/messageClient';
import minimist from 'minimist';
import { UserData } from './src/customerData';
import { from, mergeMap, concatMap, delay, of } from 'rxjs';



export default function main(params:string[]) {
    let args = minimist(params);
    
    if(args.type === 'import') {
        readCsvAndStartImport();
    }
    else {
        getSavedData();
    }
    
}


async function readCsvAndStartImport() {
  try {
    console.log("Starting import of customers")    
    const csvFilePath = path.resolve(__dirname, 'files/customers.csv');
    const headers = ['email', 'name', 'address'];
    const fileContent = fs.readFileSync(csvFilePath, { encoding: 'utf-8' });
    const saveServerClient = new SaveCustomerClient();

    parse(fileContent, {
        delimiter: ';',
        columns: headers,
        fromLine: 2
      }, (error, results: UserData[]) => {
        if (error) {
          console.error(error);
        }

        from(results)
            .pipe(
              concatMap((val) => of(val).pipe(
                delay(1000),
                mergeMap((user: UserData ) => saveServerClient.postRequest(user))
              )) 
            ).subscribe((response) => {
              console.log(response)
        })
    });

  } catch (err) {
    console.log(err);
  }
}

async function getSavedData() {
  console.log("GET SAVED DATA") 
  const getServerClient = new GetCustomersClient();
  getServerClient.getRequest().pipe(
    mergeMap( (val) => of(val).pipe(
      delay(1000)
    ))
  )
  .subscribe((response) => {
    console.log(response)
  })
}



main(process.argv);