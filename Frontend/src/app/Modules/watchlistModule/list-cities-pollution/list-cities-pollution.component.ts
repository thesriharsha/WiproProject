import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CityInfo } from 'src/app/Model/CityInfo';
import { WishlistService } from 'src/app/service/wishlist.service';
import { CityPollutionUtill } from 'src/app/util/CityPollutionUtill';
import { CityRequestData } from 'src/app/Model/CityRequestData';
import { RequestDeleteData } from 'src/app/Model/RequestDeleteData';
import { AuthenticationService } from 'src/app/service/authentication.service';
// import { weatherdata, weatherdatas } from 'src/app/util/constants';


@Component({
  selector: 'app-list-cities-pollution',
  templateUrl: './list-cities-pollution.component.html',
  styleUrls: ['./list-cities-pollution.component.css']
})
export class ListCitiesPollutionComponent  {
  util:CityPollutionUtill = new CityPollutionUtill();
  wishList:CityRequestData[]|undefined;

  

  constructor(private service:WishlistService, private authService:AuthenticationService) { 
  const observer={
    next: (result:CityRequestData[])=>{
       this.wishList=result;
    },
    error : (error:Error)=>{
      console.log("error is "+error.message);
    },
    complete: ()=>{
      console.log("completed");
    }
  }
  const observable:Observable<CityRequestData[]> = service.getWishList(this.authService.getUsername());
  observable.subscribe(observer);
  }

  remove(deleted:CityRequestData){

    const requestData:RequestDeleteData= this.util.convertToDeleteRequestData(deleted, localStorage.getItem('username'));
    const observer={
      next:( )=>{
        this.wishList?.splice(this.wishList?.indexOf(deleted),1);
      },
      error: (error:Error)=>{
        console.log("error is "+error.message);
      }
  
    }
    const observable:Observable<RequestDeleteData>=this.service.deleteFromWishList(requestData);
    observable.subscribe(observer);
    
  }

}
