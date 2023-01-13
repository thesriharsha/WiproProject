import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/Model/User';
import { AuthenticationService } from 'src/app/service/authentication.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userNameCtrl: FormControl;
  passwordCtrl: FormControl;
  myform: FormGroup;
  constructor(builder: FormBuilder, private service: AuthenticationService,private router:Router ) {
    this.userNameCtrl = builder.control('');
    this.passwordCtrl = builder.control('');
    this.myform = builder.group({
      username: this.userNameCtrl,
      password: this.passwordCtrl
      
    });
  }

  register() {
    const userName=this.userNameCtrl.value;
    const password=this.passwordCtrl.value;
    console.log(userName);
    console.log(password);
    const observer={
      next:(result:any)=>{
        alert("Successfully registered");
        this.router.navigate(['']);
      },
      error:(error:Error)=>{
        alert("Couldn't register "+error.message);
      }

    }
    const observable:Observable<any>=this.service.register(userName,password);
    observable.subscribe(observer);
    
  }
  login(){
    this.router.navigate(['/login']);
  }
  ngOnInit():void {
  
  }
}
