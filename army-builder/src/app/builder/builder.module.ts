import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {
  MatToolbarModule,
  MatButtonModule,
  MatMenuModule,
  MatIconModule,
  MatSelectModule
} from '@angular/material';

import {ArmyDomainService} from '../services/api/army-domain.service';
import {AppComponentsModule} from '../app-components/components.module';
import {BuilderRoutingModule} from './builder-routing.module';

import {BuilderComponent} from './builder/builder.component';

@NgModule({
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    MatSelectModule,

    AppComponentsModule,
    BuilderRoutingModule
  ],
  declarations: [
    BuilderComponent
  ],
  providers: [
    ArmyDomainService
  ]
})
export class BuilderModule {}
