import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styles: []
})
export class RegisterComponent implements OnInit {

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  passwordFormControl = new FormControl('', [
    Validators.required,
    Validators.minLength(6)
  ]);

  finished: boolean;

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit() {
  }

  register() {
    console.log('AuthService: register()');
    this.finished = false;
    const email = this.emailFormControl.value;
    const password = this.passwordFormControl.value;
    const result = this.authService.signUp(email, email, password);
    result.subscribe(
      response => this.processRegisterSuccess(response),
      error => this.processRegisterError(error),
      () => this.finished = true
    );
  }

  processRegisterSuccess(data: any) {
    console.log('processRegisterSuccess() ' + data);
    this.router.navigate(['/']);
  }

  processRegisterError(error: any) {
    console.log('processRegisteError() ' + error);
  }
}
