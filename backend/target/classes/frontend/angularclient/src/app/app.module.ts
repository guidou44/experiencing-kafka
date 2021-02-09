import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import {PingService} from "./service/ping.service";
import { PingComponent } from './ping/ping.component';


@NgModule({
  declarations: [
    AppComponent,
    PingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [PingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
