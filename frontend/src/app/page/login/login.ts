import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });

  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {

    if (this.loginForm.valid) {

      this.authService.login(this.loginForm.value).subscribe({

        next: (res) => {
          console.log("Connexion réussie", res);

          alert("Connexion réussie");

          // redirection vers dashboard
          this.router.navigate(['/']);
        },

        error: (err) => {
          console.error("Erreur login", err);
          alert("Email ou mot de passe incorrect");
        }

      });

    } else {
      this.loginForm.markAllAsTouched();
    }

  }


}
