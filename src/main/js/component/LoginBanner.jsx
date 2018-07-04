import React from "react";
import * as ReactDOM from "react-dom";

export class LoginBanner extends React.Component {

    constructor(props) {
        super(props);
        this.updateMode1 = this.updateMode.bind(this, 1);
        this.updateMode3 = this.updateMode.bind(this, 3);
        this.updateMode2 = this.updateMode.bind(this, 2);
        this.userLogout = this.userLogout.bind(this);
    }

    updateMode(newMode) {
        this.setState({mode: newMode});
        this.props.updateParentMode(newMode);
    }

    componentDidMount() {
        this.setState({mode: this.props.mode, user: this.props.user});
    }

    render() {
        if (!this.state) return null;
        return (this.state.user && <div className={"loginBanner"}>
            <div>
                <ul>
                    <li onClick={this.updateMode1}>Recite</li>
                    <li onClick={this.updateMode2}>Choose set</li>
                    <li onClick={this.updateMode3}>My List</li>
                </ul>
            </div>
            <div className={"loginBannerRight"}>
                <ul>
                    <li>Hello, {this.state.user}</li>
                    <li onClick={this.userLogout}>Logout</li>
                </ul>
            </div>
        </div>) || (<div className={"loginBanner"}>
            <div>
                <ul>
                    <li onClick={this.updateMode1}>Recite</li>
                    <li onClick={this.updateMode2}>Choose set</li>
                    <li onClick={this.updateMode3}>My List</li>
                </ul>
            </div>
            <div>
                <form action={"/login"} method={"get"}>
                    <input type="submit" value="Login"/>
                </form>
                <form action={"/register"} method={"get"}>
                    <input type="submit" value="Register"/>
                </form>
            </div>
        </div>)
    }

    userLogout() {
        this.setState({user: null});
    }
}