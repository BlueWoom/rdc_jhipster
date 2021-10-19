import { OnInit, Component } from '@angular/core';
import { UserService } from '../../core/user/user.service';

@Component({
  selector: 'jhi-registers',
  templateUrl: './registers.component.html',
})
export class RegistersComponent implements OnInit {
  isMaitan = false;

  constructor(protected userService: UserService) {}

  ngOnInit(): void {
    // TODO
  }
}
