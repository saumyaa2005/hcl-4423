import React from "react";

// Delicate SVG Butterfly
const Butterfly = ({ className, color = "#f472b6" }) => (
  <svg viewBox="0 0 50 50" className={className} fill="none">
    <g fill={color}>
      <path
        d="M25 24 C 18 10, 4 12, 6 24 C 7 30, 16 30, 24 26 Z"
        opacity="0.85"
      />
      <path
        d="M24 26 C 14 26, 8 36, 14 42 C 18 45, 23 38, 25 29 Z"
        opacity="0.75"
      />
      <path
        d="M25 24 C 32 10, 46 12, 44 24 C 43 30, 34 30, 26 26 Z"
        opacity="0.85"
      />
      <path
        d="M26 26 C 36 26, 42 36, 36 42 C 32 45, 27 38, 25 29 Z"
        opacity="0.75"
      />
      <ellipse cx="25" cy="27" rx="2" ry="7" fill="#831843" />
      <path
        d="M24 19 Q 20 14 18 13 M26 19 Q 30 14 32 13"
        stroke="#831843"
        strokeWidth="1.2"
        strokeLinecap="round"
      />
    </g>
  </svg>
);

const GardenElements = () => {
  return (
    <div className="pointer-events-none absolute inset-0 overflow-hidden z-0">
      {/* Soft pastel sunshine in the corner (Gentle warm glow without harsh circles) */}
      <div className="absolute -top-12 -right-12 w-80 h-80 rounded-full bg-gradient-to-br from-amber-100/70 via-yellow-100/40 to-transparent blur-3xl pointer-events-none" />

      {/* Fluttering Butterflies */}
      <div
        className="absolute top-1/4 animate-fly"
        style={{ "--fly-time": "24s", "--fly-delay": "0s" }}
      >
        <div className="animate-flutter">
          <Butterfly className="w-8 h-8 opacity-80" color="#f472b6" />
        </div>
      </div>
      <div
        className="absolute top-1/3 animate-fly"
        style={{ "--fly-time": "28s", "--fly-delay": "-12s" }}
      >
        <div className="animate-flutter">
          <Butterfly className="w-7 h-7 opacity-75" color="#c084fc" />
        </div>
      </div>

      {/* Soft Layer 1: Back Hills (Pastel Mint) */}
      <div className="absolute bottom-0 left-0 right-0 h-44 sm:h-52">
        <svg
          viewBox="0 0 1440 320"
          className="w-full h-full"
          preserveAspectRatio="none"
          fill="none"
        >
          <path
            fill="#a7f3d0"
            fillOpacity="0.45"
            d="M0,192L48,197.3C96,203,192,213,288,197.3C384,181,480,139,576,144C672,149,768,203,864,218.7C960,235,1056,213,1152,186.7C1248,160,1344,128,1392,112L1440,96L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"
          />
        </svg>
      </div>

      {/* Soft Layer 2: Middle Hills (Pastel Grass) */}
      <div className="absolute bottom-0 left-0 right-0 h-32 sm:h-40">
        <svg
          viewBox="0 0 1440 320"
          className="w-full h-full"
          preserveAspectRatio="none"
          fill="none"
        >
          <path
            fill="#6ee7b7"
            fillOpacity="0.55"
            d="M0,96L60,117.3C120,139,240,181,360,192C480,203,600,181,720,160C840,139,960,117,1080,128C1200,139,1320,181,1380,202.7L1440,224L1440,320L1380,320C1320,320,1200,320,1080,320C960,320,840,320,720,320C600,320,480,320,360,320C240,320,120,320,60,320L0,320Z"
          />
        </svg>
      </div>

      {/* Layer 3: Gentle Garden Flower Bed along the grass */}
      <div className="absolute bottom-0 left-0 right-0 h-16 sm:h-20 bg-gradient-to-t from-emerald-400/60 via-emerald-300/40 to-transparent flex items-end justify-around px-4 pb-2">
        {Array.from({ length: 22 }).map((_, i) => {
          const flowerEmojis = ["🌸", "🌷", "🌼", "🌸", "🌺", "🌷", "🌼"];
          return (
            <span
              key={i}
              className="text-lg sm:text-xl select-none animate-float-gentle opacity-85"
              style={{ animationDelay: `${(i % 5) * 0.4}s` }}
            >
              {flowerEmojis[i % flowerEmojis.length]}
            </span>
          );
        })}
      </div>
    </div>
  );
};

export default GardenElements;
