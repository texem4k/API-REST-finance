import {Component, OnInit} from '@angular/core';
import {inject} from 'vitest';
import {Router} from '@angular/router';
import {User} from '../../Model/User';
import {Transaction} from '../../Model/Transaction';
import {UserService} from '../../Service/userService';
import {TransacionService} from '../../Service/transacionService';

@Component({
  selector: 'app-home',
  templateUrl: './index.html',
  styleUrl: './index.css',
})

export class Index implements OnInit {

  //private userService = inject(UserService);
  //private router = inject(Router)
  //private transactionService = inject(TransacionService);

  users: User[] | undefined;
  transactions: Transaction[] | undefined;


  ngOnInit(): void {
  }
}
