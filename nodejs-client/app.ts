import minimist from 'minimist';
import { importWithDelay, importWithoutDelay, readCsv } from './src/importFunctions';
import { getSavedDataWithDelay, getSavedDataWithoutDelay } from './src/getCustomers';


export default function main(params:string[]) {
    let args = minimist(params);
    
    if(args.type === 'import') {
      readCsv( importWithoutDelay )
    }
    else if(args.type === 'importDelay') {
      readCsv( importWithDelay )
    }
    else if (args.type === 'getWDelay') {
      getSavedDataWithDelay();
    } 
    else {
      getSavedDataWithoutDelay();
    }
}

main(process.argv);