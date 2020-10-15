import UIKit

class DailySourceItemCell: UITableViewCell {

    @IBOutlet weak var sourceImageView: TSImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle  = .none
    }
    
}
