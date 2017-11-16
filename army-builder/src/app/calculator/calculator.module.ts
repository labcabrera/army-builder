import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatToolbarModule,
  MatButtonModule,
  MatMenuModule,
  MatIconModule
} from '@angular/material';

import { CalculatorRoutingModule } from './calculator-routing.module';
import { AppComponentsModule } from '../app-components/components.module';

import { CalculatorComponent } from './calculator/calculator.component';

@NgModule({
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,

    AppComponentsModule,
    CalculatorRoutingModule
  ],
  declarations: [
    CalculatorComponent
  ]
})
export class CalculatorModule { }
