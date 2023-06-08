import axios from "axios";

export default axios.create({
    baseURL:"http://localhost:9090/",
    headers: {
	    Authorization: "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4NDE2NTcwNSwiZXhwIjoxNjg0MTcyOTA1fQ.6xR31jSmBkcLpjH13OeBYXJDaaex5NTyzxau2Bsxb0E"
    }
    }
);
