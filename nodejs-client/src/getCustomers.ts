import { GetCustomersClient } from "./messageClient";
import { from, mergeMap, concatMap, delay, of } from 'rxjs';

export const getSavedDataWithoutDelay = () => {
    console.log("GET SAVED DATA") 
    const getServerClient = new GetCustomersClient();
    getServerClient.getRequest()
    .subscribe((response) => {
      console.log(response)
    })
}
  
  
export const getSavedDataWithDelay = () => {
    console.log("GET SAVED DATA") 
    const getServerClient = new GetCustomersClient();
    getServerClient.getRequest().pipe(
      mergeMap( (vals) => from(vals).pipe(
        concatMap((val) => of(val).pipe(
          delay(1000)
        )) 
      ))
    )
    .subscribe((response) => {
      console.log(response)
    })
}