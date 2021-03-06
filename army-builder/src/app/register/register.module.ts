import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CommonModule } from '@angular/common';

import {
  MatToolbarModule,
  MatButtonModule,
  MatMenuModule,
  MatIconModule,
  MatInputModule,
  MatCardModule,
  MatGridListModule
} from '@angular/material';

import { AuthService } from '../services/auth/auth.service';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from './register/register.component';

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
    MatGridListModule,

    RegisterRoutingModule
  ],
  declarations: [
    RegisterComponent
  ],
  providers: [
    AuthService
  ]
})
export class RegisterModule { }
