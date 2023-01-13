import { ApplicationModule, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { NavigationComponent } from './Modules/frontendModule/navigation/navigation.component';
import { LoginComponent } from './Modules/userModule/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DashboardComponent } from './Modules//frontendModule/dashboard/dashboard.component';
import { RegisterComponent } from './Modules//userModule/register/register.component';
import { SearchByCityComponent } from './Modules/applicationModule/components/search-by-city/search-by-city.component';
import { ListCitiesPollutionComponent } from './Modules/watchlistModule/list-cities-pollution/list-cities-pollution.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { WatchComponent } from './Modules/frontendModule/watch/watch.component';

import { AuthenticationInterceptor } from './request.interceptor';
//import { AuthenticationInterceptor } from './request.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    DashboardComponent,
    RegisterComponent,
    SearchByCityComponent,
    ListCitiesPollutionComponent,
    WatchComponent,
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ApplicationModule,
    BrowserAnimationsModule,
   
    
  ],
  providers: [
    {
    provide:HTTP_INTERCEPTORS,
    useClass:AuthenticationInterceptor,
    multi:true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
