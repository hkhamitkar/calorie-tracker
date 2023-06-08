import React from "react";
import loggedinuser from "../../api/user";
import userimg from "../../images/user.png"
import { Link } from "react-router-dom"


const Header = () => {
    return (
        <div className="ui fixed menu">
            <div>
            <img className="ui avatar image left" src={userimg} alt={loggedinuser.username} />
            </div>
            <div>
            <h2>{loggedinuser.username}</h2>
            </div>
            <div className="ui container right">
                <h2>Welcome to CapCal</h2>
                <div className="field" style={{marginLeft:"30px"}}>
                    <Link to="/invite">
                        <button className="ui button blue left">Invite friend</button>
                    </Link>
                </div>

            </div>
        </div>

    );
}

export default Header;