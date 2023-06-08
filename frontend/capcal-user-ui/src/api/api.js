import axios from "axios";
import loggedinuser from "../api/user"

export default axios.create({
    baseURL:"http://localhost:9090/",
     headers: {
	     Authorization: "Bearer "+loggedinuser.token
     }
    }
);
