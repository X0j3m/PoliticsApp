import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PoliticalPartyType} from '../../types/political-party.type';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class PoliticalPartyService {
  http = inject(HttpClient)

  getAll<T>(): Observable<Array<PoliticalPartyType>> {
    const url = '/api/political-parties'
    return this.http.get<Array<PoliticalPartyType>>(url)
  }

  get<T>(partyId: string): Observable<PoliticalPartyType> {
    const url = `/api/political-parties/${partyId}`
    return this.http.get<PoliticalPartyType>(url)
  }

  delete(partyId: string): Observable<any> {
    const url = `/api/political-parties/${partyId}`;
    return this.http.delete(url);
  }

  create(politicalParty: PoliticalPartyType): Observable<any> {
    const url = `/api/political-parties/${politicalParty.id}`;
    return this.http.put(url, politicalParty);
  }
}
