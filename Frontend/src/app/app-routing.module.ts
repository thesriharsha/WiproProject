import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from './gaurds/login.guard';
import { SearchByCityComponent } from './Modules/applicationModule/components/search-by-city/search-by-city.component';
import { DashboardComponent } from './Modules/frontendModule/dashboard/dashboard.component';
import { WatchComponent } from './Modules/frontendModule/watch/watch.component';


import { LoginComponent } from './Modules/userModule/login/login.component';
import { RegisterComponent } from './Modules/userModule/register/register.component';

const routes: Routes = [
  {
     path: '', component: LoginComponent 
    },
  
  {
    path: 'dashboard',component: DashboardComponent , canActivate:[LoginGuard]
  },
  {
    path: 'register',component: RegisterComponent
  },
  {
    path: 'login',component : LoginComponent
  },
  {
    path: 'watch', component:WatchComponent , canActivate:[LoginGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
