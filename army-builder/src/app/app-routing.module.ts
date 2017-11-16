import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: 'app/home/home.module#HomeModule' },
  { path: 'builder', loadChildren: 'app/builder/builder.module#BuilderModule' },
  { path: 'calculator', loadChildren: 'app/calculator/calculator.module#CalculatorModule' },
  { path: 'register', loadChildren: 'app/register/register.module#RegisterModule' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
