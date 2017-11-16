import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CommonModule } from '@angular/common';

import { AppTopMenuComponent } from './app-top-menu/top-menu.component'

import {
  MatToolbarModule,
  MatButtonModule,
  MatMenuModule,
  MatIconModule,
  MatInputModule,
  MatCardModule
} from '@angular/material';

@NgModule({
  imports: [

    CommonModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,

    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    MatInputModule,
    MatCardModule,
  ],
  declarations: [
    AppTopMenuComponent
  ],
  exports: [
    AppTopMenuComponent
  ]
})
export class AppComponentsModule { }
