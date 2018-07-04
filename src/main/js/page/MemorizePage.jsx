import React from 'react';
import {VocableList} from "../component/VocableList.jsx";

export class MemorizePage extends React.Component {
    constructor(props) {
        super(props);

        this.nextWord = () => {
            const wordList = this.state.wordList;
            const currentWordNumber = this.state.currentWordNumber;
            const nextNumber = currentWordNumber < Object.keys(wordList).length - 1 ? currentWordNumber + 1 : 0;
            this.setState({currentWordState: 0, currentWordNumber: nextNumber});
        }

        this.remember = this.remember.bind(this);
        this.forget = this.forget.bind(this);
    }

    componentDidMount() {
        if (!this.props.user) return;
        this.setState({
            currentWordNumber: 0, currentWordState: 0
        });
        this.load();
    }

    load() {
        fetch('/get-word/'.concat(this.props.user)).then(
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
                this.setState({wordList: currentWordList})
            });
    }

    render() {
        // console.log(this.state);
        if (!this.props.user) return null;
        if (!this.state || !this.state.wordList) return null;
        const list = this.state.wordList;
        const currentWord = list[this.state.currentWordNumber];
        if (!currentWord) return null;
        const wordBoard = <span className={"memorizePageBig"}>{currentWord.vocable}</span>;
        const fullWordBoard = <div><span
            className={"memorizePageBig"}>{currentWord.vocable}</span> <br/><span>{currentWord.description}</span></div>
        console.log(currentWord);

        return (<div className={"memorizePage"}>{
            // memorizing part
            (this.state.currentWordState === 0 && currentWord && <div>
                {wordBoard}<br/>
                <button onClick={this.remember}>Remember</button>
                <button onClick={this.forget}>Forget</button>
            </div>)
            // remember
            || (this.state.currentWordState === 1 && <div>
                {fullWordBoard}<br/>
                <button onClick={this.nextWord}>Next</button>
            </div>)
            // forget
            || (this.state.currentWordState === 2 && <div>
                {fullWordBoard}<br/>
                <button onClick={this.nextWord}>Next</button>
            </div>)
        }
            {/* learning condition */}
            <div className={"learningCondition"}>Your learning condition on current word sets:
                <VocableList blockDescription={false} showPoints={true} list={list}/>
            </div>
        </div>)
    }

    remember() {
        this.setState({currentWordState: 1});
        const currentWord = this.state.wordList[this.state.currentWordNumber];
        console.log("remember".concat(currentWord));
        fetch('/remember/'.concat(this.props.user).concat('/').concat(currentWord.vocable), {method: 'POST'}).then();
        this.load();
    }

    forget() {
        this.setState({currentWordState: 2});
        const currentWord = this.state.wordList[this.state.currentWordNumber];
        console.log("forget".concat(currentWord));
        fetch('/forget/'.concat(this.props.user).concat('/').concat(currentWord.vocable), {method: 'POST'}).then();
        this.load();
    }


}