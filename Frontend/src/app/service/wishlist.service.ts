import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { baseServerUrl } from '../constants';
import { CityRequestData } from '../Model/CityRequestData';
import { RequestDeleteData } from '../Model/RequestDeleteData';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  constructor(private client:HttpClient,private authService:AuthenticationService) { }

  addCityToWishList(requestData:CityRequestData):Observable<CityRequestData>{
    const url=baseServerUrl+"/wishlist/add"; 
    const observable:Observable<CityRequestData>=this.client.post<CityRequestData>(url,requestData);
    return observable;
  }

  getWishList(id:any):Observable<CityRequestData[]>{
    const url=baseServerUrl+"/wishlist/byid/"+this.authService.getUsername();
    const observable:Observable<CityRequestData[]>=this.client.get<CityRequestData[]>(url);
    return observable;
  }

  deleteFromWishList(requestData:RequestDeleteData):any{
    const url=baseServerUrl+"/wishlist/delete";
    const headers = {
      body: requestData
    };
    const observable:Observable<RequestDeleteData>=this.client.delete<RequestDeleteData>(url,headers);
    return observable;
  }

  fetchCityFromExternalServer(cityName:string,stateName:string,countryName:string):Observable<any>{
    const url=`http://api.airvisual.com/v2/city?city=${cityName}&state=${stateName}&country=${countryName}&key=c448fe8c-1ead-4ae2-8fa1-057d592639de`;
    const headers:HttpHeaders=new HttpHeaders();
    const observable: Observable<any> = this.client.get(url,{headers});
    return observable;
  }
  
}
