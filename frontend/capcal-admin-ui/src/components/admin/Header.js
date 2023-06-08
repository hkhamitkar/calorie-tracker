import React from "react";
import userimg from "../../images/user.png"

const Header = () => {
    return (
        <div className="ui fixed menu">
            <div style={{ marginRight: "5px" }}>
                <img className="ui avatar image left" src={userimg} alt="admin" />
            </div>
            <div style={{ marginRight: "50px" }}>
                <h2>admin</h2>
            </div>
            <div className="ui container right" >
                <h2>Welcome to CapCal</h2>
            </div>
        </div>

    );
}

export default Header;