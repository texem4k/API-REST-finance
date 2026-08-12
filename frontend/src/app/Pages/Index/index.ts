import {Component, inject, OnInit} from '@angular/core';
import {User} from '../../Model/User';
import {Transaction} from '../../Model/Transaction';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router';
import {SessionService} from '../../service/sessionService';
import {UserService} from '../../service/userService';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './index.html',
  styleUrl: './styles.css',
})

export class Index implements OnInit {

  users: User[] | undefined;
  transactions: Transaction[] | undefined;
  private router = inject(Router)
  protected sessionService = inject(SessionService);
  protected userService = inject(UserService);

  currentUser: User | undefined;

  createTransactionForm = new FormGroup({
    title: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(3), Validators.maxLength(32)]
    }),
    description: new FormControl('', {
      validators: [
        Validators.maxLength(64),
      ]
    }),
    amount: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.min(0.01), Validators.max(99999)]
    }),
    transactionType: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.min(0.01), Validators.max(99999)]
    }),

  });

  ngOnInit(): void {
    this.currentUser = this.sessionService.getUser();
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.users = users
      },
    })
    console.log(this.users);
  }

  protected goToLogin() {
    this.router.navigate(['/Login']);
  }

  protected goToRegister() {
    this.router.navigate(['/Register']);

  }

  protected logOut() {
    this.sessionService.logOut();
    this.router.navigate(['/']);
  }

  showForm = false;

  openForm(): void {
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
  }

  protected createTransaction() {

  }
}
