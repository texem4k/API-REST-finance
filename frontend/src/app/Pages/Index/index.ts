import {Component, inject, OnInit} from '@angular/core';
import {User} from '../../Model/User';
import {Transaction} from '../../Model/Transaction';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router';
import {SessionService} from '../../service/sessionService';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './index.html',
  styleUrl: './styles.css',
})

export class Index implements OnInit {

  users: User[] | undefined;
  transactions: Transaction[] | undefined;
  private router = inject(Router)
  protected sessionService = inject(SessionService);

  currentUser: User | undefined;

  ngOnInit(): void {
    this.currentUser = this.sessionService.getUser();
  }

  protected goToLogin() {
    this.router.navigate(['/Login']);
  }

  protected goToRegister() {
    this.router.navigate(['/Register']);

  }
}
