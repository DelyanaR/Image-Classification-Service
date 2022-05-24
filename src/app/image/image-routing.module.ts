import {ImageContainerComponent} from "./components/image-container/image-container.component";
import {GalleryComponent} from "./components/gallery/gallery.component";
import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";

const routes: Routes = [
{path: "", component: ImageContainerComponent},
{path: "gallery", component: GalleryComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImageRoutingModule { }
export const routingComponents=[ImageContainerComponent,GalleryComponent]
