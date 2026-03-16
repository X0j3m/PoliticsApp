import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MemberType} from '../../types/member.type';
import {Observable} from 'rxjs';
import {MemberRequestType} from '../../types/member-request.type';

@Injectable({
  providedIn: 'root',
})
export class MemberService {
  http = inject(HttpClient)

  getByPartyId<T>(partyId: string): Observable<Array<MemberType>> {
    const url = `/api/political-parties/${partyId}/members`
    return this.http.get<Array<MemberType>>(url);
  }

  get<T>(partyId: string, memberId: string): Observable<MemberType> {
    const url = `/api/political-parties/${partyId}/members/${memberId}`
    return this.http.get<MemberType>(url)
  }

  delete(partyId: string, memberId: string): Observable<any> {
    const url = `/api/political-parties/${partyId}/members/${memberId}`
    return this.http.delete(url);
  }

  create(member: MemberType): Observable<any> {
    let request: MemberRequestType={
      name: member.name,
      surname: member.surname,
      dateOfBirth: member.dateOfBirth,
      placeOfBirth: member.placeOfBirth,
      constituency: member.constituency
    }

    const url = `/api/political-parties/${member.politicalPartyId}/members/${member.id}`;
    console.log("wykonuje puta na "+url + " z requestem: " + JSON.stringify(request));
    return this.http.put(url, request);
  }
}
