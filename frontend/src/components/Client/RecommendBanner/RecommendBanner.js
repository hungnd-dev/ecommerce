import React from 'react';
import './RecommendBanner.css';
// import '../../App.css';
import b1 from '../../../assets/images/b1.jpg';
import b2 from '../../../assets/images/b2.jpg';
import b3 from '../../../assets/images/b3.jpg';
import b4 from '../../../assets/images/b4.jpg';
export default function RecommendBanner() {
    const handleClick = () => {
        window.scrollTo(0, 0);
    }

    return (
        <div className="RecommendBanner flex-center">
            <div className="RecommendBanner-container flex-center">

                <div className="banner-box img-hover">
                    <img src={b1} height="100%" width="100%"></img>
                    <div className="blackbox-center-container">
                        <div className="blackbox-title">Macbook</div>
                        <div className="blackbox-center">
                            {/* thẻ link đẻ chuyển */}
                            <a
                                className="blackbox-link a"
                                href="/product"
                                onClick={handleClick}
                            >Shop now
                            </a>
                        </div>
                    </div>
                </div>

                <div className="banner-box">
                    <div className="banner-top flex-center">

                        <div className="banner-top2 img-hover">
                            <img src={b2} height="100%" width="100%"></img>
                            <div className="blackbox-center-container">
                                <div className="blackbox-title blackbox-title-small">Apple</div>
                                <div className="blackbox-center">
                                    <a
                                        className="blackbox-link a"
                                        href="/product"
                                        onClick={handleClick}
                                    >Shop now
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div className="banner-top2 img-hover">
                            <img src={b3} height="100%" width="100%"></img>
                            <div className="blackbox-center-container">
                                <div className="blackbox-title blackbox-title-small">Dell</div>
                                <div className="blackbox-center">
                                    <a
                                        className="blackbox-link a"
                                        href="/product"
                                        onClick={handleClick}
                                    >Shop now
                                    </a>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div className="banner-bottom img-hover">
                        <img src={b4} height="100%" width="100%"></img>
                        <div className="blackbox-center-container">
                            <div className="blackbox-title blackbox-title-medium">Dell</div>
                            <div className="blackbox-center">
                                    <a
                                        className="blackbox-link a"
                                        href="/product"
                                        onClick={handleClick}
                                    >Shop now
                                    </a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    );
}