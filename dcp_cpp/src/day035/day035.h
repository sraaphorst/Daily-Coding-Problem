/**
 * day035.h
 *
 * By Sebastian Raaphorst, 2019.
 */

namespace dcp::day035 {
    /**
     * Given a string comprised of Rs, Gs, and Bs, sort it so that all the Rs come first, then Gs, then Bs.
     * Use only swap and constant space.
     */
    std::string &arrange_rgb_string(std::string &str) {
        int red_bound      = 0;
        int blue_bound     = str.length() - 1;
        int processing_idx = 0;

        // Idea: move all Rs to the left and Bs to the right until stable, i.e. we have processed everything up to
        // the list of Bs that has resulted.
        while (processing_idx <= blue_bound) {
            if (str[processing_idx] == 'R') {
                std::swap(str[red_bound], str[processing_idx]);
                ++red_bound;
                ++processing_idx;
            } else if (str[processing_idx] == 'G') {
                ++processing_idx;
            } else if (str[processing_idx] == 'B') {
                std::swap(str[blue_bound], str[processing_idx]);
                --blue_bound;
            }
        }

        return str;
    }
}
