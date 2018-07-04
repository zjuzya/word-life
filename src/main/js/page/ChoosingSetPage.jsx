import React from 'react';
import {VocableList} from "../component/VocableList.jsx";

export class ChoosingSetPage extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        if (!this.props.user) return;
        this.load();
    }

    render() {
        if (!this.state || !this.state.allWordLists) return null;
        // console.log(this.state.allWordLists);
        const userWordList = this.state.userWordList;
        console.log(userWordList);
        return <form onSubmit={this.handleSubmit} onChange={this.handleChange} className={"choosingSet"}>
            Pick the list you want to work on:
            {this.state.allWordLists.map(wordList => <div key={"wordList".concat(wordList.id)}>
                <input type={"radio"}
                       name={"userWordList"}
                       id={wordList.name}
                       value={wordList.name}
                       onChange={this.handleChange}/>
                <label htmlFor={wordList.name}>{wordList.name}</label><br/>
                <VocableList list={wordList.vocables} blockDescription={false} showPoints={false}/>
            </div>)}
            <input type={"submit"} value={"Confirm"}/>
        </form>
    }

    handleSubmit(event) {
        const wordList = this.state.userWordList;
        if (wordList) {
            fetch("/select-set/".concat(this.props.user).concat("?userWordList=")
                .concat(wordList.toString()), {method: "post"}).then();
        }
        this.props.changeMode(1);
        event.preventDefault();
        return false;
    }

    handleChange(event) {
        // console.log("test");
        // const updateValue = this.state.allWordLists.filter(option => document.getElementById(option.name).checked).name;
        const radios = document.getElementsByName('userWordList');
        let value;
        for (let i = 0; i < radios.length; i++) {
            // console.log(radios[i]);
            if (radios[i].type === 'radio' && radios[i].checked) {
                value = radios[i].value;
            }
        }
        this.setState({userWordList: value});
    }

    load() {
        fetch('/get-selected-set/'.concat(this.props.user)).then(
            response => {
                if (response.ok) {
                    return response;
                }
                else {
                    return {}
                }
            }
        ).then(response => response.json())
            .then(currentWordList => {
                console.log(currentWordList);
                this.setState({userWordList: currentWordList})
            });

        fetch('/get-sets/'.concat(this.props.user)).then(
            response => {
                if (response.ok) {
                    return response;
                }
                else {
                    return {};
                }
            }
        ).then(
            response => response.json()
        ).then(allSets => {
                console.log(allSets);
                this.setState({allWordLists: allSets});
            }
        )
    }
}