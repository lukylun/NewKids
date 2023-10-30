import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

export const GlobalStyles = createGlobalStyle`
    ${reset};
    
    :root {
        /* color */
        --main-color : #FF7738;
        --sub-color-1 : #4CE000;
        --sub-color-2 : #F4E000;
        --success-color : #00A3FF;
        --danger-color : #ff2449;
        --white-color : #ffffff;

        --gray-500 : #606060;
        --gray-400 : #90929E;
        --gray-300 : #AAAAAA;
        --gray-200 : #EEEEEE;
        --gray-100 : #F9F9F9;

        --black-500 : #000000;
        --black-300 : #3B3B3B;
        --black-100 : #2A2A2A;

        --footer-bg-color : #eceff3;

        /* radius */
        --radius-s : 5px;
        --radius-m : 10px;
        --radius-l : 99px;

        /* width */
        --content-width-xs : 250px;
        --content-width-s : 512px;
        --content-width-m : 756px;
        --content-width-l : 1024px;
        --content-width-xl : 1280px;
        --content-width-full : 100%;
    }

    body{
        font-family: 'Pretendard';
        padding: 0;
        margin: 0;
        overflow-y: scroll;
    };

    // 스크롤 바
     &::-webkit-scrollbar {
    } 

    a{
        text-decoration: none;
        color: inherit;
    }
    *{
        box-sizing: border-box;
    }
    html, body, div, span, h1, h2, h3, h4, h5, h6, p, 
    a, dl, dt, dd, ol, ul, li, form, label, table{
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 18px;
        vertical-align: baseline;
    }

    ol, ul{
        list-style: none;
    }
    button {
        border: 0;
        background: transparent;
        cursor: pointer;
    }
`;
