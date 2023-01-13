import { CityLocation } from "./CityLocation";
import { Pollution } from "./Pollution";
import { Weather } from "./Weather";
export class CityInfo {

  
    city: string | undefined;
    state: string | undefined;
    country: string | undefined;
    location: CityLocation | undefined;
    pollution: Pollution | undefined;
    weather: Weather | undefined;
  
}

