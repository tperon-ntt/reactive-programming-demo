import { Observable, switchMap, of, catchError, timer } from 'rxjs';
import { fromFetch } from "rxjs/fetch";
import { UserData } from "./customerData";


export class SaveCustomerClient {
    postRequest(request: UserData ): Observable<any> {   

        return fromFetch( "http://localhost:8080/api/v1/message/new/customer",{
            body: JSON.stringify(request),
            method: "post",
            headers: { "Content-Type": "application/json" }
         }).pipe(
            switchMap((response) => {
                if(response.ok) {
                  return response.json()
                }
            
                return of({ error: true, message: `Error ${ response.status }` });
            }),
            catchError(err => {
                console.error(err);
                return of({ error: true, message: err.message })
            })
        )
    }
}

export class GetCustomersClient {
    getRequest( ): Observable<any> {       
        return fromFetch( "http://localhost:8080/api/v1/message/new/all").pipe(
            switchMap((response) => {
                if(response.ok) {
                  return response.json()
                }

                return of({ error: true, message: `Error ${ response.status }` });
            }),
            catchError(err => {
                console.error(err);
                return of({ error: true, message: err.message })
            })
        )
    }
}