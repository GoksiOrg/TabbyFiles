import '../sass/app.scss'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Suspense} from "react";
import LoginPage from "./pages/LoginPage.tsx";
import LoadingSpinner from "./components/LoadingSpinner.tsx";
import TabbyFooter from "./components/TabbyFooter.tsx";

export default function App() {
    return (
        <div className="mx-auto w-auto">
            <BrowserRouter>
                <Routes>
                    <Route path={'/auth/login'} element={
                        <Suspense fallback={<LoadingSpinner/>}>
                            <LoginPage/>
                        </Suspense>
                    }/>
                </Routes>
            </BrowserRouter>
            <TabbyFooter/>
        </div>
    )
}