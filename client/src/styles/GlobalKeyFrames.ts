import { createGlobalStyle } from 'styled-components';

export const GlobalKeyFrames = createGlobalStyle`
    @keyframes appear-slide-y {
        from {
            transform: translateY(-100%);
        }
        to {
            transform: translateX(0%);
        }
    }


        @keyframes left-and-right{
            0% {
                transform: translateX(0);
            }
            50% {
                transform: translateX(-55px);
            }
            100% {
                transform: translateX(0);
            }
        }

        @keyframes right-and-left{
            0% {
                transform: translateX(0);
            }
            50% {
                transform: translateX(55px);
            }
            100% {
                transform: translateX(0);
            }
        }
    
`;
