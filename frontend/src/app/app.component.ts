import { CommonModule } from "@angular/common";
import { HttpErrorResponse } from "@angular/common/http";
import { Component } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { RouterOutlet } from "@angular/router";
import { LabseqService } from "./service/labseq.service";

@Component({
  selector: "app-root",
  standalone: true,
  imports: [CommonModule, RouterOutlet, ReactiveFormsModule, MatButtonModule, MatFormFieldModule, MatInputModule, MatCardModule],
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent {
  form: FormGroup;
  error: boolean = false;
  response: string = "";

  constructor(private labseqService: LabseqService, private fb: FormBuilder) {
    this.form = this.fb.group({
      number: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
    });
  }

  onSubmit() {
    this.labseqService.getLabseq(this.form.value.number).subscribe(
      (response: string) => {
        this.error = false;
        this.response = response;
      },
      (httpError: HttpErrorResponse) => {
        this.error = true;
        this.response = httpError.error.details ? httpError.error.details : httpError.error;
      }
    );
  }

  get isInvalid() {
    return this.form.invalid;
  }
}
