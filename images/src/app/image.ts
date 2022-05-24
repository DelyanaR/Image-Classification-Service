import {ImageTag} from "./imageTag";

export interface Image{
  id?: number;
  url: string;
  timeAdded: String;
  service: string;
  height: number;
  width: number;
  imageTags?: ImageTag[];
}
