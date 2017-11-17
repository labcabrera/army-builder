import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpModule} from '@angular/http';

import {
  MatToolbarModule,
  MatButtonModule,
  MatMenuModule,
  MatIconModule
} from '@angular/material';

import {HomeRoutingModule} from './home-routing.module';
import {AppComponentsModule} from '../app-components/components.module';
import {HomeComponent} from './home/home.component';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,

    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,

    AppComponentsModule,
    HomeRoutingModule
  ],
  declarations: [
    HomeComponent
  ],
  providers: [
  ]
})
export class HomeModule {}
