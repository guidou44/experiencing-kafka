import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PingComponent} from "./ping/ping.component";

const routes: Routes = [
  { path: 'ping', component: PingComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
