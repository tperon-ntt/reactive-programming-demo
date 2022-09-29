import { from, mergeMap, concatMap, delay, of } from 'rxjs';
import { UserData } from './customerData';
import { SaveCustomerClient } from './messageClient';
import * as fs from "fs";
import * as path from "path";
import { parse } from 'csv-parse';

export const importWithDelay = (error : any, results: UserData[]) => { 
    const saveServerClient = new SaveCustomerClient();
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
}
  
export const importWithoutDelay = (error : any, results: UserData[]) => { 
    const saveServerClient = new SaveCustomerClient();
    if (error) {
      console.error(error);
    }
  
    from(results)
        .pipe(
          mergeMap((user: UserData ) => saveServerClient.postRequest(user))
        ).subscribe((response) => {
          console.log(response)
        })
}


export const readCsv = ( fn: (error : any, results: UserData[]) => void ) => {
    try {
      console.log("Starting import of customers")    
      const csvFilePath = path.resolve(__dirname, '../files/customers.csv');
      const headers = ['email', 'name', 'address'];
      const fileContent = fs.readFileSync(csvFilePath, { encoding: 'utf-8' });
      
  
      parse(
        fileContent, 
        { delimiter: ';', columns: headers, fromLine: 2 }, 
        fn
      );
  
    } catch (err) {
      console.log(err);
    }
  }