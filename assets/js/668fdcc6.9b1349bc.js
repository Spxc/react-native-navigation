(window.webpackJsonp=window.webpackJsonp||[]).push([[179],{245:function(e,t,a){"use strict";a.r(t),a.d(t,"frontMatter",(function(){return s})),a.d(t,"metadata",(function(){return c})),a.d(t,"toc",(function(){return b})),a.d(t,"default",(function(){return l}));var n=a(3),o=a(7),i=(a(0),a(548)),r=["components"],s={id:"customBackNavigation",title:"Custom back navigation",sidebar_label:"Custom back navigation"},c={unversionedId:"docs/customBackNavigation",id:"version-7.30.0/docs/customBackNavigation",isDocsHomePage:!1,title:"Custom back navigation",description:"Back navigation lets users move backwards through the screens they previously visited.",source:"@site/versioned_docs/version-7.30.0/docs/docs-customBackNavigation.mdx",slug:"/docs/customBackNavigation",permalink:"/react-native-navigation/docs/customBackNavigation",editUrl:"https://github.com/wix/react-native-navigation/edit/master/website/versioned_docs/version-7.30.0/docs/docs-customBackNavigation.mdx",version:"7.30.0",sidebar_label:"Custom back navigation",sidebar:"version-7.30.0/docs",previous:{title:"Using functional components as screens",permalink:"/react-native-navigation/docs/functionalComponents"},next:{title:"Stack",permalink:"/react-native-navigation/docs/stack"}},b=[{value:"Additional back navigation methods",id:"additional-back-navigation-methods",children:[]},{value:"Handling the software back button",id:"handling-the-software-back-button",children:[]},{value:"Handling the hardware back button",id:"handling-the-hardware-back-button",children:[]},{value:"Disabling the iOS pop gesture",id:"disabling-the-ios-pop-gesture",children:[]}],d={toc:b};function l(e){var t=e.components,a=Object(o.a)(e,r);return Object(i.b)("wrapper",Object(n.a)({},d,a,{components:t,mdxType:"MDXLayout"}),Object(i.b)("p",null,"Back navigation lets users move backwards through the screens they previously visited."),Object(i.b)("p",null,"The most common form of back navigation is done through the ",Object(i.b)("strong",{parentName:"p"},"back button"),". The ",Object(i.b)("a",{parentName:"p",href:"stack"},"stack")," layout contains a back button by default, also known as the ",Object(i.b)("em",{parentName:"p"},"software back button"),"."),Object(i.b)("p",null,"RNN handles the back navigation for you, but there are use cases where you might need to override the default behavior of the back navigation. For example, when editing a blog post, you might want to prompt the user about unsaved changes instead of navigating to the previous destination."),Object(i.b)("h3",{id:"additional-back-navigation-methods"},"Additional back navigation methods"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},"Android devices include a ",Object(i.b)("em",{parentName:"li"},"hardware back button"),", located at the bottom of the device."),Object(i.b)("li",{parentName:"ul"},"Android 11 introduced an opt-in gesture navigation system that hides the ",Object(i.b)("a",{parentName:"li",href:"../api/options-navigationBar"},"NavigationBar"),". When enabled, the hardware back button is replaced with a back gesture performed with a horizontal swipe across the screen."),Object(i.b)("li",{parentName:"ul"},"The stack layout on iOS supports a back gesture by default. Like the android back gesture, it's performed by swiping across the screen.")),Object(i.b)("h2",{id:"handling-the-software-back-button"},"Handling the software back button"),Object(i.b)("p",null,"The software back button is located in the TopBar and is responsible for popping the top most screen in the stack. It's configured in the ",Object(i.b)("a",{parentName:"p",href:"../api/options-stack"},"TopBar")," options."),Object(i.b)("p",null,"To handle the software back button, we must first disable the default behavior which is to pop the top most screen:"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-js"},"options: {\n  topBar: {\n    backButton: {\n      popStackOnPress: false;\n    }\n  }\n}\n")),Object(i.b)("p",null,"Now, when the software back button is pressed, instead of popping the screen RNN will emit a ",Object(i.b)("a",{parentName:"p",href:"../api/events/#navigationbuttonpressed-event"},"navigationButtonPress")," event which can be handled in JS."),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-js"},"navigationButtonPressed({ buttonId }) {\n  if (buttonId === 'RNN.back') {\n    console.log('The software back button was pressed!');\n  }\n}\n")),Object(i.b)("h2",{id:"handling-the-hardware-back-button"},"Handling the hardware back button"),Object(i.b)("p",null,"Unlike the software back button which affects the stack it's part of, the hardware back button isn't bound to a specific layout. Instead, The hardware back button affects the top most layout that responds to back navigation."),Object(i.b)("p",null,"When a stack containing more than one child is focused, pressing the hardware back button will pop the stack. When a modal is displayed and the current stack has exactly one child, pressing the hardware back button will dismiss the modal."),Object(i.b)("p",null,"To handle the hardware back button, we must first disable the default behavior for the desired action (pop stack or dismiss modal) through options:"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-js"},"options: {\n  hardwareBackButton: {\n    dismissModalOnPress: false,\n    popStackOnPress: false,\n  }\n}\n")),Object(i.b)("p",null,"Once the default behaviors are disabled, RNN will emit a ",Object(i.b)("a",{parentName:"p",href:"../api/events/#navigationbuttonpressed-event"},"navigationButtonPress")," with the ",Object(i.b)("inlineCode",{parentName:"p"},"RNN.hardwareBackButton")," id."),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-js"},"navigationButtonPressed({ buttonId }) {\n  if (buttonId === 'RNN.hardwareBackButton') {\n    console.log('The hardware back button was pressed!');\n  }\n}\n")),Object(i.b)("div",{className:"admonition admonition-info alert alert--info"},Object(i.b)("div",{parentName:"div",className:"admonition-heading"},Object(i.b)("h5",{parentName:"div"},Object(i.b)("span",{parentName:"h5",className:"admonition-icon"},Object(i.b)("svg",{parentName:"span",xmlns:"http://www.w3.org/2000/svg",width:"14",height:"16",viewBox:"0 0 14 16"},Object(i.b)("path",{parentName:"svg",fillRule:"evenodd",d:"M7 2.3c3.14 0 5.7 2.56 5.7 5.7s-2.56 5.7-5.7 5.7A5.71 5.71 0 0 1 1.3 8c0-3.14 2.56-5.7 5.7-5.7zM7 1C3.14 1 0 4.14 0 8s3.14 7 7 7 7-3.14 7-7-3.14-7-7-7zm1 3H6v5h2V4zm0 6H6v2h2v-2z"}))),"Note")),Object(i.b)("div",{parentName:"div",className:"admonition-content"},Object(i.b)("p",{parentName:"div"},"The hardware back button is available on Android only."))),Object(i.b)("div",{className:"admonition admonition-info alert alert--info"},Object(i.b)("div",{parentName:"div",className:"admonition-heading"},Object(i.b)("h5",{parentName:"div"},Object(i.b)("span",{parentName:"h5",className:"admonition-icon"},Object(i.b)("svg",{parentName:"span",xmlns:"http://www.w3.org/2000/svg",width:"14",height:"16",viewBox:"0 0 14 16"},Object(i.b)("path",{parentName:"svg",fillRule:"evenodd",d:"M7 2.3c3.14 0 5.7 2.56 5.7 5.7s-2.56 5.7-5.7 5.7A5.71 5.71 0 0 1 1.3 8c0-3.14 2.56-5.7 5.7-5.7zM7 1C3.14 1 0 4.14 0 8s3.14 7 7 7 7-3.14 7-7-3.14-7-7-7zm1 3H6v5h2V4zm0 6H6v2h2v-2z"}))),"Note 2")),Object(i.b)("div",{parentName:"div",className:"admonition-content"},Object(i.b)("p",{parentName:"div"},"When gesture navigation is enabled on Android, the back gesture is handled as a hardware back press."))),Object(i.b)("h2",{id:"disabling-the-ios-pop-gesture"},"Disabling the iOS pop gesture"),Object(i.b)("p",null,"The iOS stack layout can be popped with a swipe gesture. If desired, the swipe gesture can be disabled via options:"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-js"},"options: {\n  popGesture: false;\n}\n")))}l.isMDXComponent=!0},548:function(e,t,a){"use strict";a.d(t,"a",(function(){return l})),a.d(t,"b",(function(){return h}));var n=a(0),o=a.n(n);function i(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function r(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function s(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?r(Object(a),!0).forEach((function(t){i(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):r(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function c(e,t){if(null==e)return{};var a,n,o=function(e,t){if(null==e)return{};var a,n,o={},i=Object.keys(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||(o[a]=e[a]);return o}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(o[a]=e[a])}return o}var b=o.a.createContext({}),d=function(e){var t=o.a.useContext(b),a=t;return e&&(a="function"==typeof e?e(t):s(s({},t),e)),a},l=function(e){var t=d(e.components);return o.a.createElement(b.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},u=o.a.forwardRef((function(e,t){var a=e.components,n=e.mdxType,i=e.originalType,r=e.parentName,b=c(e,["components","mdxType","originalType","parentName"]),l=d(a),u=n,h=l["".concat(r,".").concat(u)]||l[u]||p[u]||i;return a?o.a.createElement(h,s(s({ref:t},b),{},{components:a})):o.a.createElement(h,s({ref:t},b))}));function h(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var i=a.length,r=new Array(i);r[0]=u;var s={};for(var c in t)hasOwnProperty.call(t,c)&&(s[c]=t[c]);s.originalType=e,s.mdxType="string"==typeof e?e:n,r[1]=s;for(var b=2;b<i;b++)r[b]=a[b];return o.a.createElement.apply(null,r)}return o.a.createElement.apply(null,a)}u.displayName="MDXCreateElement"}}]);