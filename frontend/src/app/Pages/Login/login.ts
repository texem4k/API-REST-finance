import {Component, inject, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SessionService} from '../../service/sessionService';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {User} from '../../Model/User';
import {UserService} from '../../service/userService';
import {Router} from '@angular/router';

@Component({
  selector: 'app-logon',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})

export class Login implements OnInit {


  private sessionService = inject(SessionService);
  private userService = inject(UserService);
  private router = inject(Router);
  user: User | undefined;
  resetPassword = false;

  ngOnInit(): void {
  }

  recoverPasswordForm = new FormGroup({
    newPassword: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+\[\]{}|\\:;"'<>,.?\/]).+$/)]
    }),
    newPassword1: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email, Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+\[\]{}|\\:;"'<>,.?\/]).+$/)]
    }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email, Validators.pattern(/^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,6})$/)]
    }),
  });

  loginForm = new FormGroup({
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email, Validators.pattern(/^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,6})$/)]
    }),
    password: new FormControl('', {
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(64),
        Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+\[\]{}|\\:;"'<>,.?\/]).+$/)
      ]
    })
  });

  protected login() {
    this.userService.loginUser(this.loginForm.controls.email.getRawValue(), this.loginForm.controls.password.getRawValue()).subscribe({
      next: (userGuardado) => {
        this.sessionService.saveUser(userGuardado);
        this.router.navigate(['/']);
      },
      error: (err) => console.error(err)
    });
  }

  protected changePassword() {
    if (this.recoverPasswordForm.controls.newPassword.getRawValue() == this.recoverPasswordForm.controls.newPassword1.getRawValue()) {
      this.userService.changePassword(this.recoverPasswordForm.controls.email.getRawValue(), this.recoverPasswordForm.controls.newPassword.getRawValue()).subscribe({
        next: (userGuardado) => {
          this.sessionService.saveUser(userGuardado);
          this.router.navigate(['/']);
        },
        error: (err) => console.error(err)
      });
    }
  }
}
