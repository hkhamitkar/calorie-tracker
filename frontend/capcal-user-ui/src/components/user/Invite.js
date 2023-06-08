import React from "react";
import { Link } from "react-router-dom";
import loggedinuser from "../../api/user"
import api from "../../api/api"

class Invite extends React.Component {
    state = {
            name:"",
            email:"",
            senderName:loggedinuser.username,
            senderEmail:loggedinuser.email
    }
    
    send=async (e)=>{
        e.preventDefault();
        if(!this.isValidEmail(this.state.email)){
            alert("Invalid email");
            return;
        } 
        const response=await api.post("/user/invite",this.state);
        if(response) alert("Email sent successfully");
        this.setState({id:"",foodName:"",intakeDate:"",calories:"",username:""})  
        this.props.navigateBackHandler("/"); 
    }

     isValidEmail=(email)=> {
        return /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(email);
      }
      

    render() {
        return (
            <div className="ui main">
                <h2>Send Invitation Email</h2>
                <form className="ui form" onSubmit={this.send}>
                    <div className="field">
                        <label>Name</label>
                        <input type="text" name="name" id="name" placeholder="Name"
                            onChange={(e) => this.setState({ name: e.target.value })}
                            value={this.state.name} required />
                    </div>
                    <div className="field">
                        <label>Email</label>
                        <input type="text" name="email" id="email" placeholder="Eail"
                            onChange={(e) => this.setState({ email: e.target.value })}
                            value={this.state.email} required />
                    </div>
                    <Link to="/">
                        <button className="ui button blue left">Back</button>
                    </Link>
                    <button className="ui button blue">Send</button>
                </form>
            </div>
        );
    }
}
export default Invite;