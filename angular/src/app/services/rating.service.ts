import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RatingDTO} from "../interfaces";

@Injectable({
  providedIn: 'root'
})
export class RatingService {
  private ratingsUrl = environment.apiUrl+'/api/ratings';

  constructor(private readonly http: HttpClient) {}

  getAverageRating(agentId : number): Observable<number> {
    return this.http.get<number>(`${this.ratingsUrl}/average/${agentId}`);
  }

  createRating(ratingDTO: RatingDTO,agentId: number): Observable<RatingDTO> {
    return this.http.put<RatingDTO>(`${this.ratingsUrl}/${agentId}`, ratingDTO);
  }
}
