// Import React
import React from "react";

// Import Spectacle Core tags
import {
    BlockQuote,
    CodePane,
    Deck,
    Heading,
    Image,
    Link,
    ListItem,
    List,
    Quote,
    S,
    Slide,
    Table,
    TableRow,
    TableHeaderItem,
    TableItem,
    Text
} from "spectacle";

// Import image preloader util
import preloader from "spectacle/lib/utils/preloader";

// Import theme
import createTheme from "spectacle/lib/themes/default";

// Require CSS
require("normalize.css");
require("spectacle/lib/themes/default/index.css");


const images = {
    city: require("../assets/city.jpg"),
    kat: require("../assets/kat.png"),
    logo: require("../assets/formidable-logo.svg"),
    markdown: require("../assets/markdown.png"),
    flux: require("../assets/flux.png"),
    fluxComplex: require("../assets/flux-complex.jpg"),
    mvc: require("../assets/mvc-scale.jpg"),
    reactLifeCycle: require("../assets/react-life-cycle.png"),
    nodeLogo: require("../assets/nodejs-logo.png"),
    spectacleLogo: require("../assets/spectacle-logo.png"),
    reactDevTools: require("../assets/devtools-full.gif")
};

preloader(images);

const theme = createTheme({
    primary: "white",
    black: "#1F2022",
    lightBlue: "#03A9FC",
    lightGray: "#CECECE",
    red: "red"
}, {
    primary: "Montserrat",
    black: "Helvetica"
});

export default class Presentation extends React.Component {
    render() {
        return (
            <Deck transition={["zoom", "slide"]} transitionDuration={500} theme={theme}>
                <Slide transition={["zoom"]} bgColor="black">
                    <Heading size={1} fit caps lineHeight={1} textColor="primary">React</Heading>
                    <Text margin="10px 0 0" textColor="lightBlue" fit bold>is it a worth?</Text>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={5} textColor="lightBlue">React usage statistics</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://stackshare.io/react/in-stacks/" target="_blank" textColor="primary">
                                Who is using React?
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://github.com/facebook/react/wiki/Sites-Using-React" target="_blank" textColor="primary">
                                Sites using React
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://insights.stackoverflow.com/survey/2017#technology-most-loved-dreaded-and-wanted-frameworks-libraries-and-other-technologies" target="_blank" textColor="primary">
                                Most Loved Frameworks, Libraries and Other Technologies
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="http://stateofjs.com/2016/frontend/" target="_blank" textColor="primary">Front-end Frameworks</Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://insights.stackoverflow.com/survey/2017#technology-most-popular-languages-by-occupation" target="_blank" textColor="primary">
                                Most Popular Languages by Occupation
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://www.thoughtworks.com/de/radar/languages-and-frameworks" target="_blank" textColor="primary">
                                Languages and Frameworks
                            </Link>
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["fade"]} bgColor="black">
                    <Text textColor="primary" fit bold>
                        This presentation is using <Link href="http://formidable.com/open-source/spectacle/" target="_blank" textColor="lightBlue">Spectacle</Link>
                    </Text>
                    <Text margin="30px 0 0" textColor="lightBlue" textSize="x-large" bold>
                        A React based library for creating sleek presentations
                    </Text>
                    <Link href="https://nodejs.org" target="_blank">
                        <Image src={images.nodeLogo} margin="30px 0 0" width="25%" height="25%"/>
                    </Link>
                    <Text margin="30px 0 0" textColor="lightBlue" textSize="x-large" bold>
                        Node.js® is a JavaScript runtime built on Chrome's V8 JavaScript engine
                    </Text>
                    <Text margin="10px 0 0" textColor="primary" textSize="large" bold>
                        Node.js' package ecosystem, npm, is the largest ecosystem of open source libraries in the world
                    </Text>
                </Slide>
                <Slide transition={["fade"]} bgColor="lightBlue">
                    <Heading size={5} textColor="primary">What is React?</Heading>
                    <BlockQuote>
                        <Quote textColor="primary" textSize="xx-large">
                            React is a declarative, efficient, and flexible JavaScript library for building user
                            interfaces
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={4} textColor="lightBlue">Prior knowledge</Heading>
                    <Text margin="30px 0 0" textColor="primary" caps bold>JavaScript - ES6</Text>
                </Slide>
                <Slide transition={["zoom"]} bgColor="lightBlue">
                    <Heading size={1} fit caps lineHeight={1} textColor="primary">Fundamentals</Heading>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={4} textColor="lightBlue">Why React?</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="xx-large">Complexity of two-way data binding</ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large">
                            Bad UX from using "cascading updates" of DOM tree
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large">
                            A lot of data on a page changing over time
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large" bold>
                            Shift from MV* Frameworks mentality
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            MV* did not scale well for Facebook’s huge codebase
                        </Quote>
                        <Quote margin="20px 0 0" textSize="xx-large">
                            The main problem for Facebook was the bidirectional communication,
                            where one change can loop back and have cascading effects
                            across the codebase (making things very complicated to debug and understand)
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Image src={images.mvc}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            In general the flow inside the MV* pattern is not well defined. A lot of the bigger
                            implementations do it very differently
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="lightBlue" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">React challenges established best practices in tradicional MV* Frameworks</Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            Flux Pattern: unidirectional flow of data between a system’s components
                        </Quote>
                        <Quote margin="20px 0 0" textSize="xx-large" textColor="lightBlue">
                            Flux keeps things more predictable than MV*
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Image src={images.flux}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Image src={images.fluxComplex}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            Controller, ModelView, and View are coupled: when you change one, you often have to change
                            the others
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={5} textColor="lightBlue">Build components, not templates</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="xx-large">UI Components are the cohesive units</ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large">
                            UI description and UI logic are tightly coupled and can be collocated - they shouldn't be
                            arbitrarily decoupled
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large" bold>
                            Full power of JavaScript to express UI
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Table>
                        <TableRow>
                            <TableHeaderItem padding="10px" textSize="xx-large"/>
                            <TableHeaderItem padding="10px" textSize="xx-large">Templates</TableHeaderItem>
                            <TableHeaderItem padding="10px" textSize="xx-large" textColor="lightBlue">
                                React Components
                            </TableHeaderItem>
                        </TableRow>
                        <TableRow>
                            <TableHeaderItem padding="10px" textSize="xx-large">Separation of Concerns</TableHeaderItem>
                            <TableItem padding="10px" textSize="xx-large">Technology (JS HTML)</TableItem>
                            <TableItem padding="10px" textSize="xx-large">Responsibility "Display UI"</TableItem>
                        </TableRow>
                        <TableRow>
                            <TableHeaderItem padding="10px" textSize="8">Semantic</TableHeaderItem>
                            <TableItem padding="10px" textSize="xx-large">
                                New concepts and micro-languages (scope, directives, iterators)
                            </TableItem>
                            <TableItem padding="10px" textSize="xx-large">HTML and JavaScript</TableItem>
                        </TableRow>
                        <TableRow>
                            <TableHeaderItem padding="10px" textSize="8">Expressiveness</TableHeaderItem>
                            <TableItem padding="10px" textSize="xx-large">Underpowered</TableItem>
                            <TableItem padding="10px" textSize="xx-large">Full power of JavaScript</TableItem>
                        </TableRow>
                    </Table>
                </Slide>
                <Slide transition={["fade"]} bgColor="lightBlue" textColor="black">
                    <CodePane lang="js" source={require("raw-loader!../assets/component.example")}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="lightBlue" textColor="black">
                    <CodePane lang="js" source={require("raw-loader!../assets/component-with-no-jsx.example")}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={5} textColor="lightBlue">Component Life Cycle</Heading>
                    <Image src={images.reactLifeCycle}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={5} textColor="lightBlue">Props</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            Passed down from parent to child component
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            Represents data for the component
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            Accessed via <S type="bold">this.props</S>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large" textColor="red">
                            Properties are immutable
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={5} textColor="lightBlue">State</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            Represents internal state of the component
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            Accessed via <S type="bold">this.state</S>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large" textColor="red">
                            Treat <S type="bold">this.state</S> as if it were immutable -
                            use <Link href="https://facebook.github.io/immutable-js/" textColor="red" target="_blank"><S type="bold">Immutable</S></Link> library
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large" textColor="red">
                            Modify <S type="bold">this.state</S> using <S type="bold">this.setState</S>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            When a component's state data changes, the rendered markup
                            will be updated by re-invoking render() method
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={5} textColor="lightBlue">React</Heading>
                    <Text margin="30px 0 0" textColor="primary" fit bold>
                        Re-render everything on every update, sounds expensive?
                    </Text>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={5} textColor="lightBlue">Virtual DOM</Heading>
                    <Text margin="30px 0 0" textColor="primary" fit bold>
                        Whenever data changes, React builds a new virtual DOM subtree
                    </Text>
                    <List>
                        <ListItem margin="20px 0 0" textSize="x-large" textColor="red">diffs it with the old one</ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            computes the minimal set of DOM mutations and puts them in a queue
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large" textColor="red">and batch executes all updates</ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">and it is fast</ListItem>
                    </List>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Link href="https://github.com/facebook/react-devtools" target="_blank">
                        <Heading size={5} textColor="lightBlue">React Dev Tools</Heading>
                    </Link>
                    <Image src={images.reactDevTools}/>
                </Slide>
                <Slide transition={["zoom"]} bgColor="lightBlue">
                    <Heading size={1} fit caps lineHeight={1} textColor="primary">Ready to React?</Heading>
                    <CodePane lang="bash" source={require("raw-loader!../assets/create-react-app.example")}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="black" textColor="primary">
                    <Heading size={5} textColor="lightBlue">Live demos</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://reactjs.org/" target="_blank" textColor="primary">
                                https://reactjs.org/
                            </Link>
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["zoom"]} bgColor="lightBlue">
                    <CodePane lang="js" source={require("raw-loader!../assets/mensagem-final.example")}/>
                </Slide>
            </Deck>
        );
    }
}
