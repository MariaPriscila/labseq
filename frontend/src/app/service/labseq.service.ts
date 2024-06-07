import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment.development";

@Injectable({
  providedIn: "root",
})
export class LabseqService {
  private readonly API_URL = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getLabseq(number: number): Observable<string> {
    return this.http.get<string>(`${this.API_URL}/labseq/${number}`);
  }
}
