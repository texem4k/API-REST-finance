import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {UserService} from '../../service/userService';
import {User} from '../../Model/User';
import {Router} from '@angular/router';
import {SessionService} from '../../service/sessionService';


@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.html',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  styleUrl: './register.css'
})

export class Register {


  private userService= inject(UserService);
  private sessionService= inject(SessionService);
  private user: User | undefined;
  private router = inject(Router);

  registerForm = new FormGroup({

    username: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(4), Validators.maxLength(32), Validators.pattern(/^[a-zA-Z]{2,}$/)]
    }),
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


  protected register() {

    this.user= {name: this.registerForm.controls.username.getRawValue(),
      email: this.registerForm.controls.email.getRawValue(),
      password: this.registerForm.controls.password.getRawValue()}
    console.log(this.user.name)
    console.log(this.user.email)
    console.log(this.user.password)
    this.userService.createUser(this.user).subscribe({
      next: (userGuardado) => {
        this.sessionService.saveUser(userGuardado);
        //this.router.navigate(['/home']);
        this.router.navigate(['/']);
      },
      error: (err) => console.error(err)
    });

  }
}
