import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { baseServerUrl } from '../constants';
import { User } from '../Model/User';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

 
  constructor(private client:HttpClient) { }

  isUserLoggedIn():boolean{
    return this.getToken() != undefined && this.getToken() != null; 
}

logout():void{
  localStorage.removeItem('token');
  localStorage.removeItem('username');
}


  getUsername():any{
    const username=localStorage.getItem("username");
    return username;
  }

  getToken():any{
    const token=localStorage.getItem('token');
    return token;
  }
  

  login(username:string,password:string):Observable<string>{
     const url=baseServerUrl+'/login';
     const requestData={username,password};
     const observable:Observable<string>=this.client.post(url,requestData,{responseType:"text"}); 
     return observable;
  }
  register(username: string, password: string): Observable<string> {
    const url = baseServerUrl + '/register';
    const requestData = {
      username,
      password,
    };
    const observable: Observable<any> = this.client.post(url, requestData);
    return observable;
  }

saveToken(username:string,token:string){
 localStorage.setItem('username',username); 
 localStorage.setItem('token',token);
}
}
