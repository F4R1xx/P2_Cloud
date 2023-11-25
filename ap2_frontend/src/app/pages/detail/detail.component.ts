import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompositorService } from 'src/app/services/compositor.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  compositor: any;

  constructor(private compositorService: CompositorService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = this.route.snapshot.params["id"];
    this.compositorService.getCompositorById(id).subscribe(Response => {
      this.compositor = Response;
      console.log(this.compositor);
    });
  }

}
