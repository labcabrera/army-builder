import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {HTTP_INTERCEPTORS} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AppComponentsModule} from './app-components/components.module';
import {AuthService} from './services/auth/auth.service';
import {TokenInterceptor} from './services/auth/token-interceptor';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,

    AppRoutingModule,
    AppComponentsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AuthService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {}
