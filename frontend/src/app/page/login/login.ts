import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../service/auth/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [CommonModule,ReactiveFormsModule,RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit {
  loginForm!: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';
  isLoading: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private cd:ChangeDetectorRef
    
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
     this.errorMessage = '';
    this.successMessage = '';

    if (this.loginForm.valid) {
      this.isLoading = true;
      this.authService.login(this.loginForm.value).subscribe({
        next: (res) => {
          console.log("Connexion réussie", res);

          this.successMessage = "Connexion réussie";
          this.isLoading = false;
          this.cd.markForCheck();

        setTimeout(() => {
          this.router.navigate(['/dashboard']);
        }, 1500);
      },

        error: () => {
          console.error("Erreur login");
          this.errorMessage = "Email ou mot de passe incorrect";
          this.isLoading = false;
          this.cd.markForCheck();

        }
        

      });

    } else {
      this.loginForm.markAllAsTouched();
    }

  }


}
