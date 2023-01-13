import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { WishlistService } from 'src/app/service/wishlist.service';
import { CityPollutionUtill } from 'src/app/util/CityPollutionUtill';
import { CityRequestData } from 'src/app/Model/CityRequestData';
import { CityInfo } from '../../../../Model/CityInfo'
import { AuthenticationService } from 'src/app/service/authentication.service';


@Component({
  selector: 'app-search-by-city',
  templateUrl: './search-by-city.component.html',
  styleUrls: ['./search-by-city.component.css']
})


export class SearchByCityComponent {

  //Form Control and Mapping
  cityCtrl:FormControl;
  StateCtrl:FormControl;
  countryCtrl:FormControl;
  myform:FormGroup;

  constructor(builder: FormBuilder, private service: WishlistService, private authService:AuthenticationService) {
    this.cityCtrl= builder.control('');
    this.StateCtrl=builder.control('');
    this.countryCtrl=builder.control('');
    const mapping = {
      cityName:this.cityCtrl,
      stateName:this.StateCtrl,
      countryName:this.countryCtrl
    };
    this.myform = builder.group(mapping);

   }
 

  city!: CityInfo;

  responseCity!:CityRequestData;

  

  util:CityPollutionUtill = new CityPollutionUtill();

  //to add functionalities for delayed apperance of add to fav button
  isClicked!:boolean;

  // Add to Fav Functioanlities 
  addtofav(){
   
    const requestData:CityRequestData= this.util.convertToRequestData(this.city, this.authService.getUsername());
    const observer={
      next:(result:CityRequestData)=>{
        this.responseCity=result;
      },
      error: (error:Error)=>{
        console.log("error is "+error.message);
      }
  
    }
    const observable:Observable<CityRequestData>=this.service.addCityToWishList(requestData);
    observable.subscribe(observer);
    
          
  }


//Fetching Data From Server 
  fetchData(){
  this.isClicked=true; 
  const observer = {
    next: (result: any) => {
    
     this.city = this.util.convertToCityInfo(result.data);
     
    },
    error: (error: Error) => {
      console.log(error.message);
    },
  };
  const observable: Observable<any> = this.service.fetchCityFromExternalServer(
    this.cityCtrl.value,
    this.StateCtrl.value,
    this.countryCtrl.value
  );
  observable.subscribe(observer);
  // const Apiurl = "http://api.airvisual.com/v2/city?city="+ this.cityCtrl.value+"&state="+this.StateCtrl.value+"&country="+this.countryCtrl.value+"&key=c448fe8c-1ead-4ae2-8fa1-057d592639de"
  //    fetch(Apiurl)
  // .then(function(resp) { return resp.json() }) // Convert data to json
  // .then((data)=> {
  //   console.log(data.data.city);
  //   this.city = this.util.convertToCityInfo(data.data);
  // })
  // .catch(function() {
  //   // catch any errors
  // });

   }
  
}

