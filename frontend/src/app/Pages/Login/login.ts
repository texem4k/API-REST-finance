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

    ngOnInit(): void {
        throw new Error("Method not implemented.");
    }

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

    this.user= {name: "",
      email: this.loginForm.controls.email.getRawValue(),
      password: this.loginForm.controls.password.getRawValue()}
    this.userService.findByEmail(this.user.email).subscribe({
      next: (userGuardado) => {
        if(userGuardado.password)
        this.sessionService.saveUser(userGuardado);
        this.router.navigate(['/']);
      },
      error: (err) => console.error(err)
    });

  }
}
